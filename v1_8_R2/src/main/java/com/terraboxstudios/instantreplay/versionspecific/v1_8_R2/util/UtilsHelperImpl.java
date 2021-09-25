package com.terraboxstudios.instantreplay.versionspecific.v1_8_R2.util;

import com.terraboxstudios.instantreplay.versionspecific.util.UtilsHelper;
import net.md_5.bungee.api.chat.ClickEvent;

public class UtilsHelperImpl implements UtilsHelper {

    @Override
    public ClickEvent getTimestampMessageClickEvent(long timestamp) {
        return new ClickEvent(ClickEvent.Action.SUGGEST_COMMAND, String.valueOf(timestamp));
    }

}
