package org.moire.opensudoku.game.command;

import java.util.ListIterator;
import java.util.Stack;
import java.util.StringTokenizer;
import org.moire.opensudoku.game.CellCollection;

public class CommandStack {
	private Stack<AbstractCommand> mCommandStack = new Stack<AbstractCommand>();

	// TODO: I need cells collection, because I have to call validate on it after some
	//	commands. CellCollection should be able to validate itself on change.
	private CellCollection mCells;

	public CommandStack(CellCollection cells) {
		mCells = cells;
	}

    public static CommandStack deserialize(String data, CellCollection cells) {
        StringTokenizer st = new StringTokenizer(data, "|");
        return deserialize(st, cells);
    }

    public static CommandStack deserialize(StringTokenizer data, CellCollection cells) {
	    CommandStack result = new CommandStack(cells);
        int stackSize = Integer.parseInt(data.nextToken());
        for (int i = 0; i < stackSize; i++) {
            AbstractCommand command = AbstractCommand.deserialize(data);
            result.push(command);
        }

        return result;
    }

    public String serialize() {
        StringBuilder sb = new StringBuilder();
        serialize(sb);
        return sb.toString();
    }

    public void serialize(StringBuilder data) {
        data.append(mCommandStack.size()).append("|");
        for (int i = 0; i < mCommandStack.size(); i++) {
            AbstractCommand command = mCommandStack.get(i);
            command.serialize(data);
        }
    }

	public boolean empty() {
		return mCommandStack.empty();
	}

	public void execute(AbstractCommand command) {
		push(command);
		command.execute();
	}

	public void undo() {
		if (!mCommandStack.empty()) {
			AbstractCommand c = pop();
			c.undo();
			validateCells();
		}
	}

	public void setCheckpoint() {
		if (!mCommandStack.empty()) {
			AbstractCommand c = mCommandStack.peek();
			if (c instanceof CheckpointCommand)
				return;
		}
		push(new CheckpointCommand());
	}

	public boolean hasCheckpoint() {
		for (AbstractCommand c : mCommandStack) {
			if (c instanceof CheckpointCommand)
				return true;
		}
		return false;
	}

	public void undoToCheckpoint() {
		/*
		 * I originally planned to just call undo but this way it doesn't need to 
		 * validateCells() until the run is complete
		 */
		AbstractCommand c;
		while (!mCommandStack.empty()) {
			c = mCommandStack.pop();
			c.undo();

			if (c instanceof CheckpointCommand)
			    break;
		}
		validateCells();
	}


	public boolean hasSomethingToUndo() {
		return mCommandStack.size() != 0;
	}

	public AbstractSingleCellCommand findLatestSingleCellCommand() {
        ListIterator<AbstractCommand> iter = mCommandStack.listIterator(mCommandStack.size());
        while (iter.hasPrevious()) {
            AbstractCommand o = iter.previous();
            if (o instanceof AbstractSingleCellCommand)
                return (AbstractSingleCellCommand) o;
        }

        return null;
    }

	private void push(AbstractCommand command) {
		if (command instanceof AbstractCellCommand) {
			((AbstractCellCommand) command).setCells(mCells);
        }
		mCommandStack.push(command);
	}

	private AbstractCommand pop() {
		return mCommandStack.pop();
	}

	private void validateCells() {
		mCells.validate();
	}


}
