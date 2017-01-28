package org.firstinspires.ftc.teamcode;

import android.graphics.Color;

import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.hardware.ColorSensor;

/**
 * Created by Loaner on 1/28/2017.
 */
@Autonomous(name="DemoColor", group="Nathan")
public class DemoColor extends LinearOpMode {
 ColorSensor colorSensor ;

    @Override
    public void runOpMode() throws InterruptedException {
        telemetry.addData("here ", 0);
        waitForStart();
        int i = 0;
        while (opModeIsActive()) {
            colorSensor = hardwareMap.colorSensor.get("sensor_color");
            telemetry.addData("Clear", colorSensor.alpha());
            telemetry.addData("Red  ", colorSensor.red());
            telemetry.addData("Green", colorSensor.green());
            telemetry.addData("Blue ", colorSensor.blue());

        }
    }
}