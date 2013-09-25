package com.gamedisplay.model.event;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonTypeInfo;
import com.gamecontroller.GameState;
import com.gamedisplay.model.Event;
import com.gamedisplay.model.Sprite;
import com.gamedisplay.model.action.ReflectAction;
import com.gamedisplay.model.action.VanishAction;

/**
 * Collision events checks whether two given sprites are colliding with each
 * other. If yes, then controller can executed registered collision action
 * associated with those sprites.
 * 
 * @author Madhu
 * 
 */
@JsonTypeInfo(use = JsonTypeInfo.Id.NAME, include = JsonTypeInfo.As.PROPERTY, property = "objectType")
public class CollisionEvent extends Event {
	String name = "CollisionEvent";
	
	private final Sprite sprite1, sprite2;

	public CollisionEvent() {
		super();
		this.sprite1 = null;
		this.sprite2 = null;
	}

	public CollisionEvent(Sprite sprite1, Sprite sprite2) {
		super();
		this.sprite1 = sprite1;
		this.sprite2 = sprite2;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((name == null) ? 0 : name.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		CollisionEvent other = (CollisionEvent) obj;
		if (name == null) {
			if (other.name != null)
				return false;
		} else if (!name.equals(other.name))
			return false;
		return true;
	}

	public Sprite getSprite1() {
		return sprite1;
	}

	public Sprite getSprite2() {
		return sprite2;
	}

	@Override
	@JsonIgnore
	protected void addSupportedActions() {
		actions.add(new ReflectAction(GameState.NONE));
		actions.add(new VanishAction(GameState.NONE));
	}
}
