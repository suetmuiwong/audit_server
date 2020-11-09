package com.et.auditServer.common.utils;

import java.net.NetworkInterface;
import java.nio.ByteBuffer;
import java.nio.ByteOrder;
import java.util.Enumeration;
import java.util.UUID;

public class IdGenerator {
    /**
     * 调用该方法获取字符串ID
     * @return
     */
    public static String get() {
        //return new IdGenerator().toString();
        return UUID.randomUUID().toString().replace("-", "");
    }

    public IdGenerator() {
        _time = _gentime;
        _machine = _genmachine;
        _inc = _nextInc++;
        _new = true;
    }

    public int hashCode() {
        return _inc;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        IdGenerator that = (IdGenerator) o;
        return _time == that._time &&
                _machine == that._machine &&
                _inc == that._inc &&
                _new == that._new;
    }

    public String toStringMongod() {
        byte b[] = toByteArray();

        StringBuilder buf = new StringBuilder(24);

        for (int i = 0; i < b.length; i++) {
            int x = b[i] & 0xFF;
            String s = Integer.toHexString(x);
            if (s.length() == 1)
                buf.append("0");
            buf.append(s);
        }

        return buf.toString();
    }

    public byte[] toByteArray() {
        byte[] b = new byte[12];
        ByteBuffer bb = ByteBuffer.wrap(b);
        bb.putInt(_inc);
        bb.putInt(_machine);
        bb.putInt(_time);
        reverse(b);
        return b;
    }

    static void reverse(byte[] b) {
        for (int i = 0; i < b.length / 2; i++) {
            byte t = b[i];
            b[i] = b[b.length - (i + 1)];
            b[b.length - (i + 1)] = t;
        }
    }

    static String _pos(String s, int p) {
        return s.substring(p * 2, (p * 2) + 2);
    }

    public String toString() {
        return toStringMongod();
    }

    public int compareTo(IdGenerator id) {
        if (id == null)
            return -1;

        long xx = id.getTime() - getTime();
        if (xx > 0)
            return -1;
        else if (xx < 0)
            return 1;

        int x = id._machine - _machine;
        if (x != 0)
            return -x;

        x = id._inc - _inc;
        if (x != 0)
            return -x;

        return 0;
    }

    public int getMachine() {
        return _machine;
    }

    public long getTime() {
        long z = _flip(_time);
        return z * 1000;
    }

    public int getInc() {
        return _inc;
    }

    final int _time;
    final int _machine;
    final int _inc;

    boolean _new;

    static int _flip(int x) {
        byte[] b = new byte[4];
        ByteBuffer bb = ByteBuffer.wrap(b);
        bb.order(ByteOrder.LITTLE_ENDIAN);
        bb.putInt(x);
        bb.flip();
        bb.order(ByteOrder.BIG_ENDIAN);
        return bb.getInt();
    }

    private static int _nextInc = (new java.util.Random()).nextInt();
    private static final String _incLock = "IdGenerator._incLock";

    private static int _gentime = _flip((int) (System.currentTimeMillis() / 1000));

    static final Thread _timeFixer;
    private static final int _genmachine;
    static {
        try {
            final int machinePiece;
            {
                StringBuilder sb = new StringBuilder();
                Enumeration<NetworkInterface> e = NetworkInterface.getNetworkInterfaces();
                while (e.hasMoreElements()) {
                    NetworkInterface ni = e.nextElement();
                    sb.append(ni.toString());
                }
                machinePiece = sb.toString().hashCode() << 16;
            }

            final int processPiece = java.lang.management.ManagementFactory.getRuntimeMXBean().getName().hashCode() & 0xFFFF;
            _genmachine = machinePiece | processPiece;
        } catch (java.io.IOException ioe) {
            throw new RuntimeException(ioe);
        }

        _timeFixer = new Thread("IdGenerator-TimeFixer") {
            public void run() {
                while (true) {
                    try {
                        Thread.sleep(499);
                    } catch (InterruptedException e) {
                        System.out.println(e);
                        Thread.currentThread().interrupt();
                    }
                    _gentime = _flip((int) (System.currentTimeMillis() / 1000));
                }
            }
        };
        _timeFixer.setDaemon(true);
        _timeFixer.start();
    }

}
