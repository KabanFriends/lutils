package org.lexize.lutils.submodules.socket;

import org.lexize.lutils.LUtilsTrust;
import org.lexize.lutils.annotations.LDescription;
import org.lexize.lutils.annotations.LDocsFuncOverload;
import org.luaj.vm2.LuaError;
import org.figuramc.figura.avatar.Avatar;
import org.figuramc.figura.lua.LuaWhitelist;

import java.io.IOException;
import java.net.Socket;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

@LuaWhitelist
@LDescription("Socket submodule")
public class LSocket {
    private final Avatar avatar;
    private static final LuaError NO_PERMISSION = new LuaError("This avatar don't have permission to open Socket connection");
    private static final HashMap<Avatar, List<LSocketClient>> createdSockets = new HashMap<>();
    public static void clearAvatarSockets(Avatar a) throws IOException {
        if (createdSockets.containsKey(a)) {
            List<LSocketClient> clients = createdSockets.get(a);
            for (var client :
                    clients) {
                client.close();
            }
        }
    }
    public LSocket(Avatar avatar) {
        this.avatar = avatar;
    }
    @LuaWhitelist
    @LDocsFuncOverload(
            argumentTypes = {String.class, int.class},
            argumentNames = {"ip", "port"},
            returnType = LSocketClient.class,
            description = "Opens connection by specified IP and port"
    )
    public LSocketClient connect(String ip, int port) throws IOException {
        permissionCheck();
        var socket = new LSocketClient(new Socket(ip, port));
        if (!createdSockets.containsKey(avatar)) {
            createdSockets.put(avatar, new ArrayList<>());
        }
        createdSockets.get(avatar).add(socket);
        return socket;
    }
    private void permissionCheck() {
        if (!canOpenSocketConnection()) throw NO_PERMISSION;
    }
    @LuaWhitelist
    public boolean canOpenSocketConnection() {
        return avatar.permissions.get(LUtilsTrust.SOCKET_PERMISSION) > 0;
    }
}
