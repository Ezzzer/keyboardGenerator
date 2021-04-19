package com.ez;

import java.awt.*;
import java.awt.event.KeyEvent;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Scanner;

public class Main {

    public static void promptEnterKey() {
        Scanner scanner = new Scanner(System.in);
        scanner.nextLine();
    }

    public static void main(String[] args) throws IOException {

        try {
            String template = Files.readString(Path.of("milkdrop.txt"), StandardCharsets.US_ASCII);
            long startTime = System.currentTimeMillis();
            SimpleDateFormat sdf = new SimpleDateFormat("mm:ss");
            Date date;
            long sTime;
            for (String s : template.split("\n")) {

                String text[] = s.split("-");
                switch (text[0]) {
                    case "#":
                        continue;
                    case "t":
                        date = sdf.parse(text[1]);
                        sTime = date.getSeconds() * 1000 + date.getMinutes() * 60 * 1000;
                        while (System.currentTimeMillis() - startTime < sTime) {
                            Thread.sleep(1000);
                        }
                        break;
                    case "i":
                        date = sdf.parse(text[1]);
                        sTime = date.getSeconds() * 1000 + date.getMinutes() * 60 * 1000;
                        long intervalStart = System.currentTimeMillis();
                        while (System.currentTimeMillis() - intervalStart < sTime) {
                            Thread.sleep(1000);
                        }
                        break;
                }
                type(text[2]);
                System.out.println(s);
                //promptEnterKey();172
            }
        } catch (AWTException e) {
            e.printStackTrace();
        } catch (InterruptedException | IOException | ParseException e) {
            e.printStackTrace();
        }
    }


    public static void type(String str) throws AWTException, InterruptedException {
        Activate.setFocus();
        Robot robot = new Robot();
        robot.delay(10);
        Activate.setFocus();
        for (char ch : str.toCharArray()) {
            System.out.print(ch);

            if (Character.isSpaceChar(ch)) {
                continue;
            }
            if (ch == 'd') {
                robot.keyPress(KeyEvent.VK_DELETE);
                robot.keyRelease(KeyEvent.VK_DELETE);
            } else if (ch == 'f') {
                robot.keyPress(KeyEvent.VK_SPACE);
                robot.keyRelease(KeyEvent.VK_SPACE);
            } else if (ch == 'b') {
                robot.keyPress(KeyEvent.VK_BACK_SPACE);
                robot.keyRelease(KeyEvent.VK_BACK_SPACE);
            } else if (Character.isUpperCase(ch)) {
                robot.keyPress(KeyEvent.VK_SHIFT);
                robot.keyPress((int) ch);
                robot.keyRelease((int) ch);
                robot.keyRelease(KeyEvent.VK_SHIFT);
            } else if (Character.isLetterOrDigit(ch)) {
                char upCh = Character.toUpperCase(ch);
                robot.keyPress((int) upCh);
                robot.keyRelease((int) upCh);
            }
            robot.delay(20);
        }
    }
}
