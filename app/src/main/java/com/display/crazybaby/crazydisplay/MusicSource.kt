package com.display.crazybaby.crazydisplay

import android.content.Context
import android.database.Cursor
import com.crazybabyluna.walle.musiclib.pojo.TrackItem
import android.provider.MediaStore
import android.util.Log
import android.view.InputDevice.getDevice
import android.content.ContentUris
import android.net.Uri


/**
 * Created by walle on 2017/7/7.
 */
class MusicSource {
    fun getMusicList(context: Context) :List<TrackItem>{
        val trackItems = mutableListOf<TrackItem>()
        val audioColumns = arrayOf(MediaStore.Audio.Media._ID, //0
                MediaStore.Audio.Media.DISPLAY_NAME, //1
                MediaStore.Audio.Media.TITLE, //2
                MediaStore.Audio.Media.DURATION, //3
                MediaStore.Audio.Media.ARTIST, //4
                MediaStore.Audio.Media.ALBUM, //5
                MediaStore.Audio.Media.YEAR, //6
                MediaStore.Audio.Media.MIME_TYPE, //7
                MediaStore.Audio.Media.SIZE, //8d
                MediaStore.Audio.Media.DATA, //9
                MediaStore.Audio.Media.ALBUM_ID)
        var cursor = context.contentResolver.query(MediaStore.Audio.Media.EXTERNAL_CONTENT_URI, audioColumns, null, null, null);
        if (cursor.moveToFirst()) {
            do {
//                val id = ContentTree.AUDIO_PREFIX + cursor.getInt(cursor.getColumnIndex(MediaStore.Audio.Media._ID))
                val title = cursor.getString(cursor.getColumnIndexOrThrow(MediaStore.Audio.Media.TITLE))
                val creator = cursor.getString(cursor.getColumnIndexOrThrow(MediaStore.Audio.Media.ARTIST))
                val filePath = cursor.getString(cursor.getColumnIndexOrThrow(MediaStore.Audio.Media.DATA))
                val mimeType = cursor.getString(cursor.getColumnIndexOrThrow(MediaStore.Audio.Media.MIME_TYPE))
                val size = cursor.getLong(cursor.getColumnIndexOrThrow(MediaStore.Audio.Media.SIZE))
                val duration = cursor.getLong(cursor.getColumnIndexOrThrow(MediaStore.Audio.Media.DURATION))
                val album = cursor.getString(cursor.getColumnIndexOrThrow(MediaStore.Audio.Media.ALBUM))
//                val res = Res(MimeType(mimeType.substring(0,
//                        mimeType.indexOf('/')), mimeType.substring(mimeType
//                        .indexOf('/') + 1)), size, "http://"
//                        + mediaServer.getAddress() + "/" + id)
//                val url = "http://" + mediaServer.getAddress() + "/" + id
//                Log.d("url==", url)
//                res.setDuration((duration / (1000 * 60 * 60)).toString() + ":" + duration % (1000 * 60 * 60) / (1000 * 60) + ":" + duration % (1000 * 60) / 1000)

                // Music Track must have `artist' with role field, or
                // DIDLParser().generate(didl) will throw nullpointException
                if (duration/1000<60)
                    continue

                val trackItem = readData(cursor)

                trackItem.setAlbum(album)
                trackItem.setDuration(duration)

                trackItems.add(trackItem)
                Log.d("trackItem", trackItem.toString())
                //                Log.v(LOGTAG, "added audio item " + title + "from " + filePath);
            } while (cursor.moveToNext())
            //		}

        }

        cursor.close()
        return trackItems

    }

    private fun readData(cursor: Cursor): TrackItem {
        //        MediaMetadataCompat.Builder mediaMetadataBuilder = new MediaMetadataCompat.Builder();
        val musicInfo = TrackItem()
        //        musicInfo.setId(cursor.getString(0));
        //        musicInfo.setBasicAlbum(cursor.getString(5));
        musicInfo.setName(cursor.getString(2))
        musicInfo.setDuration(cursor.getInt(3).toLong())
        musicInfo.setPalyUrl(cursor.getString(9))
        musicInfo.setSubtitle(cursor.getString(4))
        val uris = getMusicPicUri(cursor.getLong(0), cursor.getLong(10))
        musicInfo.setImageUrl(uris.toString())
        return musicInfo
    }

    private val sArtworkUri = Uri.parse("content://media/external/audio/albumart")
    private fun getMusicPicUri(songid: Long, albumid: Long): Uri {
        var uri: Uri? = null

        if (albumid < 0 && songid < 0) {
            throw IllegalArgumentException("Must specify an album or a song id")
        }
        if (albumid < 0) {
            uri = Uri.parse("content://media/external/audio/media/$songid/albumart")

        } else {
            uri = ContentUris.withAppendedId(sArtworkUri, albumid)

        }

        return uri
    }
}