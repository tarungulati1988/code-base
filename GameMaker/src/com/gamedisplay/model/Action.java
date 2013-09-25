package com.gamedisplay.model;

import com.fasterxml.jackson.annotation.JsonSubTypes;
import com.fasterxml.jackson.annotation.JsonTypeInfo;
import com.gamedisplay.model.action.FallThrough;
import com.gamedisplay.model.action.MoveAction;
import com.gamedisplay.model.action.MoveLeftAction;
import com.gamedisplay.model.action.MoveRightAction;
import com.gamedisplay.model.action.ReflectAction;
import com.gamedisplay.model.action.VanishAction;

/**
 * Action interface that specifies minimum contract required for any kind of
 * action. For all non standard object accesses, use constructor to pass the
 * value.
 * 
 * @author Amey, Madhu
 * 
 */
@JsonTypeInfo(use = JsonTypeInfo.Id.NAME, include = JsonTypeInfo.As.PROPERTY, property = "objectType")
@JsonSubTypes({ @JsonSubTypes.Type(value = MoveLeftAction.class),
		@JsonSubTypes.Type(value = MoveRightAction.class),
		@JsonSubTypes.Type(value = MoveAction.class) ,
		@JsonSubTypes.Type(value = ReflectAction.class),
		@JsonSubTypes.Type(value = VanishAction.class),
		@JsonSubTypes.Type(value = FallThrough.class)})
public interface Action {
	public String performAction(Sprite sprite);
	@Override
	public int hashCode();
	@Override
	public boolean equals(Object obj);
}
