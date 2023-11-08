package com.example.myapplication.Model;

public class WizardController extends EnemyController {
    public Enemy createEnemy() {
        return new Wizard();
    }
}
