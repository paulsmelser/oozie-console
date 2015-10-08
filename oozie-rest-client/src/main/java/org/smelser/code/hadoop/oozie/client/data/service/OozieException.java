package org.smelser.code.hadoop.oozie.client.data.service;

/**
 * Created by paul.smelser@gmail.com on 06/10/15.
 *
 * @author psmelser
 */
public class OozieException extends Exception{
    public OozieException(Throwable throwable){
        super(throwable);
    }

    public OozieException(String message, Throwable throwable){
        super(message, throwable);
    }
}
