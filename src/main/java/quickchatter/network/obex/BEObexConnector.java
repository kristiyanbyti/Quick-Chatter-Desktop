/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package quickchatter.network.bluetooth.obex;

import org.jetbrains.annotations.NotNull;
import quickchatter.network.bluetooth.basic.BEConnector;
import quickchatter.network.bluetooth.basic.BEError;
import quickchatter.network.bluetooth.basic.BESocket;
import quickchatter.utilities.Callback;

public interface BEObexConnector extends BEConnector {
    void connect(@NotNull Callback<BESocket> success, @NotNull Callback<Exception> failure) throws Exception, BEError;
    void stop();
    
    interface Receiver extends BEObexConnector {
        
    }
    
    interface Sender extends BEObexConnector {
        
    }
}
