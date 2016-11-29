package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.vuforia.HINT;
import com.vuforia.Vuforia;

import org.firstinspires.ftc.robotcore.external.ClassFactory;
import org.firstinspires.ftc.robotcore.external.matrices.OpenGLMatrix;
import org.firstinspires.ftc.robotcore.external.matrices.VectorF;
import org.firstinspires.ftc.robotcore.external.navigation.VuforiaLocalizer;
import org.firstinspires.ftc.robotcore.external.navigation.VuforiaTrackable;
import org.firstinspires.ftc.robotcore.external.navigation.VuforiaTrackableDefaultListener;
import org.firstinspires.ftc.robotcore.external.navigation.VuforiaTrackables;

@Autonomous(name="Nathan Opmode", group="Nathan")  // @Autonomous(...) is the other common choice
public class NathanOp extends LinearOpMode {
    @Override
    public void runOpMode() throws InterruptedException {
        VuforiaLocalizer.Parameters params = new VuforiaLocalizer.Parameters(R.id.cameraMonitorViewId);
        params.cameraDirection = VuforiaLocalizer.CameraDirection.BACK;
        params.vuforiaLicenseKey = "Af8mJUL/////AAAAGamRrtduq0AYioQ8+Iqr61Uf6oyUkAu3UyV/z77f3FXEb3oRQHebg2Mb+1EEk/hWjAHiAAwNyvsn74IIHK6foNJh6jGB/YqZ7k/vKaQ8nYW3PGoKBLgT8/qVT8pJ6TcUcQ81IBgPTxJ9LT5scw989dT3tolt4Y3Jybgo9Yb5DW9tPT4xc/Qb/UkJ/WQt0Z4e/PP/l8BhUhX2JN8kpPHi+P/J3/qH/XHUhkf5rYTLf3ZMESKbovJ+ieUdMcXKLtyeKhjvlZ+WStZ27XE/eMmfQHWeK+qmKg9EusYQEVLiptwKHSIAsMfCH04xqDA8pIrwIVCuGN9M+763IDBnb7P4GabnYOiQuJtAar2rnhTcnFNF";
        params.cameraMonitorFeedback = VuforiaLocalizer.Parameters.CameraMonitorFeedback.AXES;
        VuforiaLocalizer vuforia = ClassFactory.createVuforiaLocalizer(params);
        Vuforia.setHint(HINT.HINT_MAX_SIMULTANEOUS_IMAGE_TARGETS, 4);

        VuforiaTrackables beacons = vuforia.loadTrackablesFromAsset("FTC_2016-17");
        beacons.get(0).setName("Wheels");
        beacons.get(1).setName("Tools");
        beacons.get(2).setName("Lego");
        beacons.get(3).setName("Gears");

        waitForStart();

        beacons.activate();

        while (opModeIsActive()) {
            for (VuforiaTrackable beac : beacons) {
                OpenGLMatrix pose = ((VuforiaTrackableDefaultListener) beac.getListener()).getPose();

                if (pose != null) {
                    VectorF translation = pose.getTranslation();

                    telemetry.addData(beac.getName() + "-Translation", translation);
                    double degreesToTurn = Math.toDegrees(Math.atan2(translation.get(1), translation.get(2)));
                    ;

                    telemetry.addData(beac.getName() + "-Degrees", degreesToTurn);

                }

            }
        }
        telemetry.update();

    }


}
