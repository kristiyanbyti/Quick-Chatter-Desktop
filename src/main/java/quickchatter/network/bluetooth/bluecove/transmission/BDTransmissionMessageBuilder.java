package quickchatter.network.bluetooth.bluecove.transmission;

import org.jetbrains.annotations.NotNull;

import quickchatter.network.basic.TransmissionMessage;
import quickchatter.network.basic.TransmissionMessagePart;
import quickchatter.network.basic.TransmissionType;

import java.util.ArrayList;
import java.util.List;
import org.jetbrains.annotations.Nullable;

public class BDTransmissionMessageBuilder {
    public final @NotNull TransmissionType type;

    private final @NotNull BDTransmissionMessagePartBuilder _messageBuilder;

    public BDTransmissionMessageBuilder(@NotNull TransmissionType type) {
        this.type = type;
        this._messageBuilder = new BDTransmissionMessagePartBuilder(type);
    }

    public @Nullable TransmissionMessage buildFromMessageParts(@NotNull List<TransmissionMessagePart> parts) {
        @Nullable ArrayList<TransmissionMessagePart.Data> currentMessageData = null;

        for (TransmissionMessagePart part : parts) {
            if (part instanceof TransmissionMessagePart.Start) {
                currentMessageData = new ArrayList<>();
            }

            if (part instanceof TransmissionMessagePart.Data) {
                TransmissionMessagePart.Data data = (TransmissionMessagePart.Data)part;

                if (currentMessageData != null) {
                    currentMessageData.add(data);
                }
            }

            if (part instanceof TransmissionMessagePart.End) {
                if (currentMessageData != null) {
                    return new BDTransmissionMessage(_messageBuilder.buildDataFromList(currentMessageData));
                }

                currentMessageData = null;
            }
        }

        return null;
    }
}
