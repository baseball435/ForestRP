package com.common.movable;

public interface MovableMovementInterface {
	void Enter(Movable M);

	void Entered(Movable M);

	void Exit(Movable M);

	void Exited(Movable M);

	void Move();
}
