package pl.mrfisherman.musicvoter.service.file;

import com.thoughtworks.xstream.XStream;
import com.thoughtworks.xstream.io.xml.StaxDriver;
import org.springframework.stereotype.Component;
import pl.mrfisherman.musicvoter.MusicVoterApplication;
import pl.mrfisherman.musicvoter.model.Song;
import pl.mrfisherman.musicvoter.model.SongsWrapper;

import java.io.IOException;
import java.io.StringReader;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.List;

@Component
public class XmlParser implements SongParser {

    @Override
    public List<Song> parseSongsFromPath(Path path) {
        XStream xStream = new XStream(new StaxDriver());
        XStream.setupDefaultSecurity(xStream);
        xStream.allowTypesByWildcard(new String[] { MusicVoterApplication.class.getPackageName() + ".**" });
        xStream.alias("song", Song.class);
        xStream.alias("songs", SongsWrapper.class);
        xStream.addImplicitCollection(SongsWrapper.class, "listOfSongs");
        List<Song> songs = new ArrayList<>();
        try(StringReader stringReader = new StringReader(Files.readString(path).replaceAll("[&_]", ""))) {
            SongsWrapper songsWrapper = (SongsWrapper) xStream.fromXML(stringReader);
            songs = songsWrapper.getListOfSongs();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return songs;
    }

    public String parse(Object content) {
        XStream xStream = new XStream(new StaxDriver());
        removePackageNamesForStream(xStream);
        return xStream.toXML(content);
    }

    private void removePackageNamesForStream(XStream xStream) {
        xStream.aliasPackage("", "pl.mrfisherman.music_voter.model");
        xStream.aliasPackage("", "pl.mrfisherman.music_voter.model.pojo.report");
    }
}
