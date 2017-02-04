package org.firstinspires.ftc.teamcode;

import android.graphics.Color;
import android.media.Ringtone;
import android.media.RingtoneManager;
import android.net.Uri;
import android.util.Log;

import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.hardware.ColorSensor;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.util.ElapsedTime;
import com.qualcomm.robotcore.util.RobotLog;

import org.firstinspires.ftc.robotcontroller.external.samples.HardwarePushbot;

import static org.firstinspires.ftc.teamcode.R.id.log;

/**
 * Created by Loaner on 1/28/2017.
 */
@Autonomous(name = "DemoColor", group = "Nathan")
public class DemoColor extends LinearOpMode {
    public static final String TAG = "DemoColor";
    ColorSensor colorSensor;

    DcMotor leftMotor = null;
    DcMotor rightMotor = null;
    static final double FORWARD_SPEED = 0.08;
    static final double TURN_SPEED = 0.5;
    /* Declare OpMode members. */
    HardwarePushbot robot = new HardwarePushbot();   // Use a Pushbot's hardware
    private ElapsedTime runtime = new ElapsedTime();

    @Override
    public void runOpMode() throws InterruptedException {
        telemetry.addData("here ", 0);
        telemetry.update();
        RobotLog.ii(TAG, "Here...!", 0);
        waitForStart();
        int i = 0;
        leftMotor = hardwareMap.dcMotor.get("left_drive");
        rightMotor = hardwareMap.dcMotor.get("right_drive");
        colorSensor = hardwareMap.colorSensor.get("sensor_color");
        double speed = FORWARD_SPEED;
        Uri notification = RingtoneManager.getDefaultUri(RingtoneManager.TYPE_NOTIFICATION);
        Ringtone r = RingtoneManager.getRingtone(hardwareMap.appContext, notification);
        r.play();

        while (opModeIsActive() && (runtime.seconds() < 5.9)) {
            leftMotor.setPower(speed);
            rightMotor.setPower(-speed);
//            RobotLog.ii(TAG, String.format("Red: %d",colorSensor.red ()), 0);
//            RobotLog.ii(TAG, String.format("Green: %d",colorSensor.green()), 0);
//            RobotLog.ii(TAG, String.format("Blue: %d",colorSensor.blue ()), 0);
//            RobotLog.ii(TAG, String.format("Clear: %d",colorSensor.alpha()), 0);

            telemetry.addData("Clear", colorSensor.alpha());
            telemetry.addData("Red  ", colorSensor.red());
            telemetry.addData("Green", colorSensor.green());
            telemetry.addData("Blue ", colorSensor.blue());

            if (colorSensor.green() < 3 &&
                    colorSensor.red() < 3 &&
                    colorSensor.blue() < 3) {
                telemetry.addData("Found Grey!", 0);
                RobotLog.ii(TAG, "grey");
            } else {
                telemetry.addData("Found White!", 0);
                RobotLog.ii(TAG, "white");
                notification = RingtoneManager.getDefaultUri(RingtoneManager.TYPE_NOTIFICATION);
                r = RingtoneManager.getRingtone(hardwareMap.appContext, notification);
                r.play();
                speed = 0 ;

            }
            telemetry.addData("Path", "Leg 1: %2.5f S Elapsed", runtime.seconds());
            telemetry.update();

        }
    }
}