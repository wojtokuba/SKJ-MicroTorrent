package pl.wojtokuba.proj.Utils.Net;

public class Communicates {
    public static final byte EHLO = 0x10;
    public static final byte FILE = 0x11;
    public static final byte FIN = 0x12;
    public static final byte PULL_P2P = 0x13;
    public static final byte PUSH_P2P = 0x14;
    public static final byte PULL_P2MP = 0x15;
    public static final byte PUSH_P2MP = 0x16;
}

//client request packets structure:
/*
    1 byte - communicate type
    EHLO:
        2 bytes - (short) clientID
    FILE:
        32 bytes - (int) fileChecksum
 */

//server response packets structure:
/*
    1 byte - communicate type
    EHLO:
        2 bytes - (short) clientID
    FILE:
        32 bytes - (int) fileChecksum
 */