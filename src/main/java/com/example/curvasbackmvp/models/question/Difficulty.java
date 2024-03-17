package com.example.curvasbackmvp.models.question;

import lombok.Getter;

@Getter
public enum Difficulty {
    EASY(10),
    MEDIUM(20),
    HARD(30);

    private final int pointsValue;

    Difficulty(int pointsValue) {
        this.pointsValue = pointsValue;
    }

    @Override
    public String toString() {
        return "Difficulty{" +
                "pointsValue=" + pointsValue +
                '}';
    }
}
