package com.aresstack.api;

import com.aresstack.api.markerInterfaces.WDType;

public interface WDCommandResponse<T> extends WDType {
    String getType(); // "success" oder "error"
    int getId(); // ID des ursprünglichen Commands
    T getResult(); // ✅ Generisches Ergebnis-Objekt (bei Erfolg)
}
