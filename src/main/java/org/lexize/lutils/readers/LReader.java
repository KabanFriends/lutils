package org.lexize.lutils.readers;

import org.figuramc.figura.lua.LuaWhitelist;

import java.io.InputStream;

public abstract class LReader<T> {
    @LuaWhitelist
    public abstract T readFrom(InputStream stream);
}
