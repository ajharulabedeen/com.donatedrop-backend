package com.donatedrop.util;

import java.util.HashMap;
import java.util.Map;
import javax.naming.ldap.HasControls;

public class MapSingleton
    {
        private static Map map;
        
        public static Map getMap()
            {
                if(map == null)
                  {
                    map = new HashMap();
                  }
                return map;
            }
    }
