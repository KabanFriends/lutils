package org.lexize.lutils;

import org.figuramc.figura.entries.FiguraPermissions;
import org.figuramc.figura.entries.annotations.FiguraPermissionsPlugin;
import org.figuramc.figura.permissions.Permissions;

import java.util.Collection;
import java.util.List;

@FiguraPermissionsPlugin
public class LUtilsTrust implements FiguraPermissions {
    public static final Permissions HTTP_PERMISSION = new Permissions("http",0,0,0,1,1);
    public static final Permissions SOCKET_PERMISSION = new Permissions("socket",0,0,0,1,1);
    @Override
    public String getTitle() {
        return "lutils";
    }

    @Override
    public Collection<Permissions> getPermissions() {
        return List.of(
                HTTP_PERMISSION,
                SOCKET_PERMISSION
        );
    }
}
