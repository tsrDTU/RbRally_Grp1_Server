package dk.dtu.compute.se.pisd.roborally.model.actions;


import dk.dtu.compute.se.pisd.roborally.model.Maps.GoldStripe;
import dk.dtu.compute.se.pisd.roborally.model.Player;

import java.util.Hashtable;

public class checkpoint implements ICheckPoint {

    Hashtable<String, Integer> Location = new Hashtable<String, Integer>();

    @Override
    public void numCheckPoints() {
       Location.put( "1N" , 1);
       Location.put( "2N" , 2);
       Location.put( "3N" , 3);

    }

    @Override
    public void locationCheckPoints() {

    }



}
