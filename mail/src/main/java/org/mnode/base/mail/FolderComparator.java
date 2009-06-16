/*
 * Created on 21/05/2003
 *
 * Copyright 2003.
 */
package org.mnode.base.mail;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

import javax.mail.Folder;

/**
 * @author Ben Fortuna
 * 
 */
public class FolderComparator implements Comparator<Folder> {

	public static final List<String> INBOX_NAMES = new ArrayList<String>();
	static {
		INBOX_NAMES.add("inbox");
	}

	public static final List<String> OUTBOX_NAMES = new ArrayList<String>();
	static {
		OUTBOX_NAMES.add("outbox");
	}

	public static final List<String> SENT_NAMES = new ArrayList<String>();
	static {
		SENT_NAMES.add("sent");
		SENT_NAMES.add("sent items");
		SENT_NAMES.add("sent mail");
	}

	public static final List<String> DELETED_NAMES = new ArrayList<String>();
	static {
		DELETED_NAMES.add("deleted");
		DELETED_NAMES.add("trash");
	}
	
	public int compare(Folder folder1, Folder folder2) {
		if (INBOX_NAMES.contains(folder1.getName().toLowerCase())) {
			return Integer.MIN_VALUE;
		}
		else if (INBOX_NAMES.contains(folder2.getName().toLowerCase())) {
			return Integer.MAX_VALUE;
		}
		else if (OUTBOX_NAMES.contains(folder1.getName().toLowerCase())) {
			return Integer.MIN_VALUE;
		}
		else if (OUTBOX_NAMES.contains(folder2.getName().toLowerCase())) {
			return Integer.MAX_VALUE;
		}
		else if (SENT_NAMES.contains(folder1.getName().toLowerCase())) {
			return Integer.MIN_VALUE;
		}
		else if (SENT_NAMES.contains(folder2.getName().toLowerCase())) {
			return Integer.MAX_VALUE;
		}
		else if (DELETED_NAMES.contains(folder1.getName().toLowerCase())) {
			return Integer.MAX_VALUE;
		}
		else if (DELETED_NAMES.contains(folder2.getName().toLowerCase())) {
			return Integer.MIN_VALUE;
		}
		return folder1.getName().compareTo(folder2.getName());
	}

}
