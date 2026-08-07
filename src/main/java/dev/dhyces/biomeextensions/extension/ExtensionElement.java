package dev.dhyces.biomeextensions.extension;

import com.mojang.serialization.Codec;

import java.util.List;

import dev.dhyces.biomeextensions.ApiAccess;

public interface ExtensionElement {
    Codec<ExtensionElement> DISPATCH_CODEC = ApiAccess.getInstance().getTypeCodec().dispatch(ExtensionElement::getType, ExtensionElementType::getCodec);
    Codec<List<ExtensionElement>> LIST_CODEC = DISPATCH_CODEC.listOf().fieldOf("effects").codec();

    ExtensionElementType<?> getType();
}
