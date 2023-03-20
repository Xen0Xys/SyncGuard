# SyncGuard
**SyncGuard** is a simple backup tool written in Java that helps you maintain a backup of a given folder. With **SyncGuard**, you can easily create and manage backups of your important files and folders.

## Download
You can download the latest release of SyncGuard from the [Github releases](https://github.com/Xen0Xys/SyncGuard/releases) page. Look for the latest release version and download the corresponding artifact.

## How to Use
To use **SyncGuard**, simply run the following command in your terminal:

```sh
java -jar SyncGuard.jar <sourceFolder> <backupFolder> <duration|s m h d w>
```
Replace `<sourceFolder>` with the path to the folder you want to back up, `<backupFolder>` with the path to the folder where you want to store the backups, and `<duration|s m h d w>` with the duration of the backup interval.

For example, to create a backup of the folder `~/Documents` every day, you would run the following command:

```sh
java -jar SyncGuard.jar ~/Documents ~/Backups/daily/ 1d
```
## Backup Duration Formats
You can specify the backup duration using the following formats:

* `s` for seconds
* `m` for minutes
* `h` for hours
* `d` for days
* `w` for weeks

For example, to create a backup every 30 minutes, you would run the following command:

```sh
java -jar SyncGuard.jar ~/Documents ~/Backups/every-30-minutes/ 30m
```

## License
**SyncGuard** is licensed under the [GNU General Public License v3.0](https://github.com/Xen0Xys/SyncGuard/blob/main/LICENSE). Feel free to use, modify, and distribute it however you like, as long as you adhere to the terms of the GPL v3.
