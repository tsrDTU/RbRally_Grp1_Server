package dk.dtu.compute.se.pisd.roborally.fileaccess;

import dk.dtu.compute.se.pisd.roborally.fileaccess.model.BoardTemplate;
import dk.dtu.compute.se.pisd.roborally.model.Board;
import org.springframework.boot.json.AbstractJsonParser;
import org.springframework.boot.json.JsonParseException;
import org.springframework.boot.json.JsonParser;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.List;
import java.util.Map;
/*
public class GameJsonParser extends AbstractJsonParser {

    public GameJsonParser(Game s)
    {
      //  parseMap(s, BoardTemplate)
        Map <String, Board> gm = ( LoadBoard.createBoardFromTemplate(@RequestBody)) ;
    }

    @Override
    public Map<String, Object> parseMap(String json) throws JsonParseException {
        return null;
    }

    @Override
    public List<Object> parseList(String json) throws JsonParseException {
        return null;
    }
}

 */
