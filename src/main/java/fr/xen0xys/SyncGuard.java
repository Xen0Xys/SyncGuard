package fr.xen0xys;

import fr.xen0xys.models.fs.SGFolder;
import fr.xen0xys.models.time.TimeParser;
import fr.xen0xys.systems.BackupSystem;

import java.io.File;

public class SyncGuard {

    public static void main(String[] args){
        if(args.length < 2){
            System.out.println("Usage: java -jar SyncGuard.jar <sourceFolder> <backupFolder> <duration|s m h d w>");
            System.exit(1);
        }
        SGFolder sourceFolder = new SGFolder(new File(args[0]));
        SGFolder backupFolder = new SGFolder(new File(args[1]));
        long duration = TimeParser.parse(args[2]);
        BackupSystem backupSystem = new BackupSystem(backupFolder, sourceFolder);
        while(true){
            System.out.println("##########################################");
            System.out.println("#                Syncing...              #");
            System.out.println("##########################################");
            backupSystem.sync();
            try {
                Thread.sleep(duration);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
}
