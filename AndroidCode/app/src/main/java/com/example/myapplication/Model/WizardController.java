package com.example.myapplication.Model;

public class WizardController extends EnemyController {
    @Override
    public Enemy createEnemy() {
        return new Wizard();
    }
}
