/**
 * This file is part of Base Modules.
 *
 * Copyright (c) 2009, Ben Fortuna [fortuna@micronode.com]
 *
 * Base Modules is free software: you can redistribute it and/or modify
 * it under the terms of the GNU General Public License as published by
 * the Free Software Foundation, either version 3 of the License, or
 * (at your option) any later version.
 *
 * Base Modules is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU General Public License for more details.
 *
 * You should have received a copy of the GNU General Public License
 * along with Base Modules.  If not, see <http://www.gnu.org/licenses/>.
 */
package org.mnode.base.mail;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

import javax.mail.Folder;

/**
 * @author Ben Fortuna
 * 
 */
public class FolderComparator implements Comparator<Folder> {

    /**
     * A list of names of inbox folders.
     */
    public static final List<String> INBOX_NAMES ;
    static {
        List<String> names = new ArrayList<String>();
        names.add("inbox");
        INBOX_NAMES = Collections.unmodifiableList(names);
    }

    /**
     * A list of names of outbox folders.
     */
    public static final List<String> OUTBOX_NAMES;
    static {
        List<String> names = new ArrayList<String>();
        names.add("outbox");
        OUTBOX_NAMES = Collections.unmodifiableList(names);
    }

    /**
     * A list of names of sent message folders.
     */
    public static final List<String> SENT_NAMES;
    static {
        List<String> names = new ArrayList<String>();
        names.add("sent");
        names.add("sent items");
        names.add("sent mail");
        SENT_NAMES = Collections.unmodifiableList(names);
    }

    /**
     * A list of names of deleted message folders.
     */
    public static final List<String> DELETED_NAMES;
    static {
        List<String> names = new ArrayList<String>();
        names.add("deleted");
        names.add("trash");
        DELETED_NAMES = Collections.unmodifiableList(names);
    }

    /**
     * {@inheritDoc}
     */
    public int compare(Folder folder1, Folder folder2) {
        if (INBOX_NAMES.contains(folder1.getName().toLowerCase())) {
            return Integer.MIN_VALUE;
        } else if (INBOX_NAMES.contains(folder2.getName().toLowerCase())) {
            return Integer.MAX_VALUE;
        } else if (OUTBOX_NAMES.contains(folder1.getName().toLowerCase())) {
            return Integer.MIN_VALUE;
        } else if (OUTBOX_NAMES.contains(folder2.getName().toLowerCase())) {
            return Integer.MAX_VALUE;
        } else if (SENT_NAMES.contains(folder1.getName().toLowerCase())) {
            return Integer.MIN_VALUE;
        } else if (SENT_NAMES.contains(folder2.getName().toLowerCase())) {
            return Integer.MAX_VALUE;
        } else if (DELETED_NAMES.contains(folder1.getName().toLowerCase())) {
            return Integer.MAX_VALUE;
        } else if (DELETED_NAMES.contains(folder2.getName().toLowerCase())) {
            return Integer.MIN_VALUE;
        }
        return folder1.getName().compareTo(folder2.getName());
    }

}
