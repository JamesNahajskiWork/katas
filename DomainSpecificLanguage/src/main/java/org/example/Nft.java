package org.example;

import java.time.Duration;
import java.util.function.Function;

public class Nft {

    TestType testType = TestType.Soak;
    public Nft deployApp() {
        System.out.println("Just deployed the app");
        return this;
    }

    public Nft deployMocks() {
        System.out.println("Just deployed some mocks");
        return this;
    }

    public Nft runTest(TestType testType) {
        System.out.println("About to run a " + testType.name() + " test");
        this.testType = testType;
        return this;
    }

    public Nft forDurationMinutes(int duration) {
        System.out.println("Running a " + testType.name() + " test for " + duration + " minutes");
        return this;
    }

    public Nft scaleDown() {
        System.out.println("Scaling everything down");
        return this;
    }

    public Nft parallel(Function asd) {
return this;
    }

    public static void doDeploy() {

    }

    public static Object doDeploy(Object o) {
    }


    enum TestType {
        Soak,
        Load,
        RollingDeploy
    }
}
