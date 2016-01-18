package org.projectlemon.intenseorange.model.utilities.PDU;

import java.io.InputStream;

/**
 * Created by linuslagerhjelm on 16-01-01.
 */
public abstract class PDU {
    //TODO: implement this class with methods and other cool stuff

    public static PDU fromInputStream(InputStream inputStream){
        //TODO: Implement
        throw new UnsupportedOperationException("Not yet implemented");
    }
    public abstract byte[] toByteArray();
}
