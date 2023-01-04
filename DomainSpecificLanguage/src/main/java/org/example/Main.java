package org.example;

import java.time.Duration;
import java.time.temporal.ChronoUnit;
import java.util.function.Function;

import static java.time.temporal.ChronoUnit.MINUTES;
import static org.example.Nft.TestType.RollingDeploy;
import static org.example.Nft.TestType.Soak;

public class Main {
    public static void main(String[] args) {
        System.out.println("Hello world!");

        Step
                .nft()
                .deployApp()
                .deployMocks()
                .runTest(Soak)
                .forDurationMinutes(30)
                .scaleDown();

        new Nft()
                .deployApp()
                .deployMocks()
                .parallel(
                        NftHelpers.doDeploymentAfter(5)
                )
                .runTest(RollingDeploy)
                .forDurationMinutes(30)
                .scaleDown();
    }

    private static Function deployNewVersion() {
    }
}