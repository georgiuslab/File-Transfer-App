package ge.edu.sangu.giorgi.datatransferapp.controllers;

import com.jcraft.jsch.JSch;
import com.jcraft.jsch.JSchException;
import com.jcraft.jsch.Session;

public class JschConnectionService {

    public Session connect(String username, String host, int port, String password) throws JSchException {
        JSch jsch = new JSch();
        Session session = jsch.getSession(username, host, port);
        session.setPassword(password);
        session.setConfig("StrictHostKeyChecking", "no");
        session.connect();
        return session;
    }
}