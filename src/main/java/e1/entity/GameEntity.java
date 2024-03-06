package e1.entity;

import e1.movement.Movable;

public interface GameEntity extends Movable{

    GameType getType();
}