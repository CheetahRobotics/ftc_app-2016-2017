package org.firstinspires.ftc.teamcode;

import android.graphics.Color;
import android.util.Log;

import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.hardware.ColorSensor;
import com.qualcomm.robotcore.util.RobotLog;

import static org.firstinspires.ftc.teamcode.R.id.log;

/**
 * Created by Loaner on 1/28/2017.
 */
@Autonomous(name="DemoColor", group="Nathan")
public class DemoColor extends LinearOpMode {
    public static final String TAG = "DemoColor";
 ColorSensor colorSensor ;

    @Override
    public void runOpMode() throws InterruptedException {
        telemetry.addData("here ", 0);
        RobotLog.ii(TAG, "Here...!", 0);
        waitForStart();
        int i = 0;
        while (opModeIsActive()) {
//            RobotLog.ii(TAG, "nathan is my father !", 2);
            colorSensor = hardwareMap.colorSensor.get("sensor_color");
//            RobotLog.ii(TAG, String.format("Red: %d",colorSensor.red ()), 0);
//            RobotLog.ii(TAG, String.format("Green: %d",colorSensor.green()), 0);
//            RobotLog.ii(TAG, String.format("Blue: %d",colorSensor.blue ()), 0);
//            RobotLog.ii(TAG, String.format("Clear: %d",colorSensor.alpha()), 0);

            telemetry.addData("Clear", colorSensor.alpha());
            telemetry.addData("Red  ", colorSensor.red());
            telemetry.addData("Green", colorSensor.green());
            telemetry.addData("Blue ", colorSensor.blue());

            if (colorSensor.green() < 10 &&
                    colorSensor.red() < 10 &&
                    colorSensor.blue() < 10) {
                telemetry.addData("Found Grey!", 0);
                RobotLog.ii(TAG, "grey");
            }
            else {
                telemetry.addData("Found White!", 0);
                RobotLog.ii(TAG, "white");
            }
            telemetry.update();
        }
    }
}
