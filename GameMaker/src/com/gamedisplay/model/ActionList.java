package com.gamedisplay.model;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonTypeInfo;

/**
 * Handy class to keep list actions. Jackson is stupid and sometimes doesn't
 * play well with Map<key,arraylist<value>> hence we need this structure.
 * 
 * @author Amey
 * 
 */
@JsonTypeInfo(use = JsonTypeInfo.Id.NAME, include = JsonTypeInfo.As.PROPERTY, property = "objectType")
public class ActionList implements Iterable<Action> {

	private List<Action> list;

	public ActionList() {
		this.list = new ArrayList<Action>();
	}

	public ActionList(List<Action> list) {
		this.setList(list);
	}

	public List<Action> getList() {
		return list;
	}

	public void setList(List<Action> list) {
		this.list = list;
	}

	public void add(Action action) {
		this.list.add(action);
	}

	public void removeAll() {
		this.list.clear();
	}

	@Override
	public Iterator<Action> iterator() {
		return this.list.iterator();
	}
}
