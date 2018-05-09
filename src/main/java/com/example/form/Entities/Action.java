package com.example.form.Entities;


import java.util.Set;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;

@Entity
public class Action {
	@Id
	private int ID;
	private String ActionName;
	private String ActionEntity;
	private int EntityID;
	private int Time;
	
	public int getID() {
		return ID;
	}
	public void setID(int iD) {
		ID = iD;
	}
	public String getActionName() {
		return ActionName;
	}
	public void setActionName(String actionName) {
		ActionName = actionName;
	}
	public String getActionEntity() {
		return ActionEntity;
	}
	public void setActionEntity(String actionEntity) {
		ActionEntity = actionEntity;
	}
	public int getEntityID() {
		return EntityID;
	}
	public void setEntityID(int entityID) {
		EntityID = entityID;
	}
	public int getTime() {
		return Time;
	}
	public void setTime(int time) {
		Time = time;
	}
	
	public Action(int iD, String actionName, String actionEntity, int entityID, int time) {
		super();
		ID = iD;
		ActionName = actionName;
		ActionEntity = actionEntity;
		EntityID = entityID;
		Time = time;
	}
	
	

	


}