/* 
 * Copyright (C) 2009 Roman Masek
 * 
 * This file is part of OpenSudoku.
 * 
 * OpenSudoku is free software: you can redistribute it and/or modify
 * it under the terms of the GNU General Public License as published by
 * the Free Software Foundation, either version 3 of the License, or
 * (at your option) any later version.
 * 
 * OpenSudoku is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU General Public License for more details.
 * 
 * You should have received a copy of the GNU General Public License
 * along with OpenSudoku.  If not, see <http://www.gnu.org/licenses/>.
 * 
 */

package org.moire.opensudoku.game.command;

import java.util.StringTokenizer;

/**
 * Generic interface for command in application.
 *
 * @author romario
 */
public abstract class AbstractCommand {

    private interface CommandCreatorFunction {
        AbstractCommand create();
    }

    private static class CommandDef {
        String mLongName;
        String mShortName;
        CommandCreatorFunction mCreator;

        public CommandDef(String longName, String shortName, CommandCreatorFunction creator){
            mLongName = longName;
            mShortName = shortName;
            mCreator = creator;
        }

        public AbstractCommand create() {
            return mCreator.create();
        }

        public String getLongName() {
            return mLongName;
        }

        public String getShortName() {
            return mShortName;
        }
    }

    private static final CommandDef[] commands = {
            new CommandDef(ClearAllNotesCommand.class.getSimpleName(),"c1",
                    new CommandCreatorFunction() { public AbstractCommand create() {return new ClearAllNotesCommand();} }),
            new CommandDef(EditCellNoteCommand.class.getSimpleName(),"c2",
                    new CommandCreatorFunction() { public AbstractCommand create() {return new EditCellNoteCommand();} }),
            new CommandDef(FillInNotesCommand.class.getSimpleName(),"c3",
                    new CommandCreatorFunction() { public AbstractCommand create() {return new FillInNotesCommand();} }),
            new CommandDef(SetCellValueCommand.class.getSimpleName(),"c4",
                    new CommandCreatorFunction() { public AbstractCommand create() {return new SetCellValueCommand();} }),
            new CommandDef(CheckpointCommand.class.getSimpleName(),"c5",
                    new CommandCreatorFunction() { public AbstractCommand create() {return new CheckpointCommand();} })
    };

	public static AbstractCommand deserialize(StringTokenizer data) {
		String cmdShortName = data.nextToken();
        for (CommandDef cmdDef: commands) {
            if (cmdDef.getShortName().equals(cmdShortName)) {
                AbstractCommand cmd = cmdDef.create();
                cmd._deserialize(data);
                return cmd;
            }
        }
        throw new IllegalArgumentException(String.format("Unknown command class '%s'.", cmdShortName));
    }

    protected void _deserialize(StringTokenizer data) {

    }

    public void serialize(StringBuilder data) {
        String cmdLongName = getCommandClass();
        for (CommandDef cmdDef: commands) {
            if (cmdDef.getLongName().equals(cmdLongName)) {
                data.append(cmdDef.getShortName()).append("|");
                return;
            }
        }

        throw new IllegalArgumentException(String.format("Unknown command class '%s'.", cmdLongName));
    }

	public String getCommandClass() {
		return getClass().getSimpleName();
	}

	/**
	 * Executes the command.
	 */
	abstract void execute();

	/**
	 * Undo this command.
	 */
	abstract void undo();

}
