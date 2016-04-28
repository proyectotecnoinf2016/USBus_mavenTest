package com.tecnoinf.proy.logic.auth;
import com.tecnoinf.proy.dataAccess.UserDAO;

import java.util.HashMap;
import java.util.Map;
import java.util.UUID;
import java.security.GeneralSecurityException;
import javax.security.auth.login.LoginException;

/**
 * Created by JuanPablo on 4/28/2016.
 */

public final class Authenticator {

    private static Authenticator authenticator = null;

    //Esto hay que ponerlo en la colección Sessions que va a tener todas las sessiones activas con un indice
    //ttl que se encargara de irlas limpiando cada cierto tiempo.
    //también podríamos manejarlas en httpsession hay que investigar!!

    private final Map<String, String> authorizationTokensStorage = new HashMap();

    private Authenticator() {

    }

    public static Authenticator getInstance() {
        if ( authenticator == null ) {
            authenticator = new Authenticator();
        }

        return authenticator;
    }

    public String login( long tenantId, String username, String password ) throws LoginException {
            UserDAO udao = new UserDAO();
            if (udao.validatePassword(tenantId,username,password)){
                String authToken = UUID.randomUUID().toString();
                authorizationTokensStorage.put( authToken, username );
                return authToken;
            }
        throw new LoginException( "Error al autenticar el usuario." );
    }

    public boolean isAuthTokenValid( long tenantId, String username, String authToken ) {
        return true;
    }

    public void logout( String authToken ) throws GeneralSecurityException {
        authorizationTokensStorage.remove( authToken );
        return;
    }
}