package com.mir00r.todoapp.commons;

import javax.sound.sampled.LineUnavailableException;
import java.io.File;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class Commons {
    private Commons() {
    }

//    public static void beep() {
//        try {
//            SoundUtils.tone(500, 500, 0.1);
//        } catch (LineUnavailableException e) {
//            System.out.println("Couldn't play boot up beep..");
//        }
//    }

    public static List<String> deleteImageAndRebuildPaths(List<String> imagePaths, int imageIndex) {
        List<String> newPaths = new ArrayList<>();
        for (int i = 0; i < imagePaths.size(); i++) {
            if (i != imageIndex)
                newPaths.add(imagePaths.get(i));
            else {
                File file = new File(imagePaths.get(imageIndex));
                if (file.exists()) file.delete();
            }
        }
        return newPaths;
    }


    public static <T> T getLastElement(final Iterable<T> elements) {
        final Iterator<T> itr = elements.iterator();
        T lastElement = itr.next();

        while (itr.hasNext()) {
            lastElement = itr.next();
        }

        return lastElement;
    }

}
