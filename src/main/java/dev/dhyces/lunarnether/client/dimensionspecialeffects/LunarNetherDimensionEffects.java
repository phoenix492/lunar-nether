package dev.dhyces.lunarnether.client.dimensionspecialeffects;

import com.mojang.blaze3d.platform.GlStateManager;
import com.mojang.blaze3d.systems.RenderSystem;
import com.mojang.blaze3d.vertex.BufferBuilder;
import com.mojang.blaze3d.vertex.BufferUploader;
import com.mojang.blaze3d.vertex.DefaultVertexFormat;
import com.mojang.blaze3d.vertex.MeshData;
import com.mojang.blaze3d.vertex.Tesselator;
import com.mojang.blaze3d.vertex.VertexBuffer;
import com.mojang.blaze3d.vertex.VertexFormat;
import net.minecraft.client.Camera;
import net.minecraft.client.multiplayer.ClientLevel;
import net.minecraft.client.renderer.DimensionSpecialEffects;
import net.minecraft.client.renderer.GameRenderer;
import net.minecraft.client.renderer.ShaderInstance;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.util.Mth;
import net.minecraft.util.RandomSource;
import net.minecraft.world.phys.Vec3;

import dev.dhyces.lunarnether.LunarNether;
import dev.dhyces.lunarnether.LunarNetherClient;
import dev.dhyces.lunarnether.server.saveddata.LunarTimeData;
import dev.dhyces.lunarnether.util.ColorUtil;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import org.joml.Matrix4f;
import org.joml.Vector3d;

public class LunarNetherDimensionEffects extends DimensionSpecialEffects {
    private static final ResourceLocation SUN_LOCATION = ResourceLocation.withDefaultNamespace("textures/environment/sun.png");
    private static final ResourceLocation OVERWORLD_LOCATION = ResourceLocation.fromNamespaceAndPath(LunarNether.MODID, "textures/environment/overworld_phases.png");
    private static final ResourceLocation OVERWORLD_GLOW_LOCATION = ResourceLocation.fromNamespaceAndPath(LunarNether.MODID, "textures/environment/overworld_glow.png");

    private static VertexBuffer skyBuffer;
    private static VertexBuffer starsBuffer;

    static {
        setup();
    }

    public LunarNetherDimensionEffects() {
        super(Float.NaN, true, DimensionSpecialEffects.SkyType.NORMAL, false, true);
    }

    static void setup() {
        skyBuffer = new VertexBuffer(VertexBuffer.Usage.STATIC);
        starsBuffer = new VertexBuffer(VertexBuffer.Usage.STATIC);

        Tesselator tesselator = Tesselator.getInstance();

        skyBuffer.bind();
        skyBuffer.upload(drawSky(tesselator));
        VertexBuffer.unbind();

        starsBuffer.bind();
        starsBuffer.upload(drawStars(tesselator));
        VertexBuffer.unbind();
    }

    @Override
    public @NotNull Vec3 getBrightnessDependentFogColor(@NotNull Vec3 pFogColor, float pBrightness) {
        return pFogColor;
    }

    @Override
    public boolean isFoggyAt(int pX, int pY) {
        return pY < 128;
    }

    @Override
    public float @Nullable [] getSunriseColor(float pTimeOfDay, float pPartialTicks) {
        return null;
    }


    @Override
    public boolean renderSky(ClientLevel level, int ticks, float partialTick, Matrix4f modelViewMatrix, Camera camera, Matrix4f projectionMatrix, boolean isFoggy, Runnable setupFog) {
        if (camera.getPosition().y < 128) {
            return false;
        }

        Tesselator tesselator = Tesselator.getInstance();


        Matrix4f starMatrix = new Matrix4f(modelViewMatrix);
        Matrix4f sunMatrix =  new Matrix4f(modelViewMatrix);


        // setup for sun and stars
        starMatrix.rotateY((float) Math.toRadians(-90F));
        sunMatrix.rotateY((float) Math.toRadians(-90F));

        // rotate for time of day
        float timeAngle = LunarTimeData.netherTimeOfDay(LunarNetherClient.netherDayTime) * 360.0F;
        starMatrix.rotateX((float) Math.toRadians(timeAngle));
        sunMatrix.rotateX((float) Math.toRadians(timeAngle));

        // render stars
        RenderSystem.setShader(GameRenderer::getPositionColorShader);
        ShaderInstance posColorShader = RenderSystem.getShader();
        if (posColorShader != null) {
            starsBuffer.bind();
            starsBuffer.drawWithShader(starMatrix, projectionMatrix, posColorShader);
            VertexBuffer.unbind();
        }

        // render sun
        float sunSize = 30f;
        RenderSystem.enableBlend();
        RenderSystem.blendFuncSeparate(GlStateManager.SourceFactor.SRC_ALPHA, GlStateManager.DestFactor.ONE, GlStateManager.SourceFactor.ONE, GlStateManager.DestFactor.ZERO);
        RenderSystem.setShader(GameRenderer::getPositionTexShader);
        RenderSystem.setShaderTexture(0, SUN_LOCATION);

        BufferBuilder sunBuilder = tesselator.begin(VertexFormat.Mode.QUADS, DefaultVertexFormat.POSITION_TEX);
        sunBuilder.addVertex(sunMatrix, -sunSize, 100, -sunSize).setUv(0, 0);
        sunBuilder.addVertex(sunMatrix, sunSize, 100, -sunSize).setUv(1, 0);
        sunBuilder.addVertex(sunMatrix, sunSize, 100, sunSize).setUv(1, 1);
        sunBuilder.addVertex(sunMatrix, -sunSize, 100, sunSize).setUv(0, 1);
        BufferUploader.draw(sunBuilder.build());

        // setup for overworld
        Matrix4f overworldMatrix = new Matrix4f(modelViewMatrix);
        Matrix4f overworldGlowMatrix = new Matrix4f(modelViewMatrix);

        overworldMatrix.rotateY((float) Math.toRadians(-90F));
        overworldGlowMatrix.rotateY((float) Math.toRadians(-90F));

        //how many degrees up from the west is it, 0 is below you.
        //125 is the exact middle of the first moon phase because
        //the sun is measured from the bottom of the sprite but the earth is measured from the middle,
        //resulting in a difference of 12.5 degrees from where it would be.
        overworldMatrix.rotateX((float) Math.toRadians(-125.0F));
        overworldGlowMatrix.rotateX((float) Math.toRadians(-125.0F));

        // render earth
        float overworldSize = 30f;
        int phase = (int)(level.dayTime() * 7 / 24000 % 8L);
        int x = phase % 4;
        int y = phase / 4 % 2;
        float minU = (float) (x) / 4.0F;
        float minV = (float) (y) / 2.0F;
        float maxU = (float) (x + 1) / 4.0F;
        float maxV = (float) (y + 1) / 2.0F;
        RenderSystem.defaultBlendFunc();
        RenderSystem.setShaderTexture(0, OVERWORLD_LOCATION);
        BufferBuilder overworldBuilder = tesselator.begin(VertexFormat.Mode.QUADS, DefaultVertexFormat.POSITION_TEX);
        overworldBuilder.addVertex(overworldMatrix, -overworldSize, -100, overworldSize).setUv(maxU, maxV);
        overworldBuilder.addVertex(overworldMatrix, overworldSize, -100, overworldSize).setUv(minU, maxV);
        overworldBuilder.addVertex(overworldMatrix, overworldSize, -100, -overworldSize).setUv(minU, minV);
        overworldBuilder.addVertex(overworldMatrix, -overworldSize, -100, -overworldSize).setUv(maxU, minV);
        BufferUploader.drawWithShader(overworldBuilder.build());

        int eclipsePhase = (level.getMoonPhase() + 4) % 8;
        int eclipseX = eclipsePhase % 4;
        int eclipseY = eclipsePhase / 4 % 2;
        float eclipseMinU = (float) (eclipseX) / 4.0F;
        float eclipseMinV = (float) (eclipseY) / 2.0F;
        float eclipseMaxU = (float) (eclipseX + 1) / 4.0F;
        float eclipseMaxV = (float) (eclipseY + 1) / 2.0F;
        RenderSystem.setShaderTexture(0, OVERWORLD_GLOW_LOCATION);
        BufferBuilder overworldGlowBuilder = tesselator.begin(VertexFormat.Mode.QUADS, DefaultVertexFormat.POSITION_TEX);
        overworldGlowBuilder.addVertex(overworldGlowMatrix, -overworldSize, -100, overworldSize).setUv(eclipseMaxU, eclipseMaxV);
        overworldGlowBuilder.addVertex(overworldGlowMatrix, overworldSize, -100, overworldSize).setUv(eclipseMinU, eclipseMaxV);
        overworldGlowBuilder.addVertex(overworldGlowMatrix, overworldSize, -100, -overworldSize).setUv(eclipseMinU, eclipseMinV);
        overworldGlowBuilder.addVertex(overworldGlowMatrix, -overworldSize, -100, -overworldSize).setUv(eclipseMaxU, eclipseMinV);
        BufferUploader.drawWithShader(overworldGlowBuilder.build());

        return true;
    }

    private static MeshData drawSky(Tesselator tesselator) {
        BufferBuilder builder = tesselator.begin(VertexFormat.Mode.QUADS, DefaultVertexFormat.POSITION_COLOR);

        int distance = 2000;

        // draw a black cube instead of the default sky
        builder.addVertex(-distance, -distance, -distance);
        builder.addVertex(-distance, -distance, distance);
        builder.addVertex(distance, -distance, distance);
        builder.addVertex(distance, -distance, -distance);

        builder.addVertex(-distance, distance, -distance);
        builder.addVertex(-distance, -distance, -distance);
        builder.addVertex(distance, -distance, -distance);
        builder.addVertex(distance, distance, -distance);

        builder.addVertex(-distance, -distance, distance);
        builder.addVertex(-distance, distance, distance);
        builder.addVertex(distance, distance, distance);
        builder.addVertex(distance, -distance, distance);

        builder.addVertex(distance, -distance, -distance);
        builder.addVertex(distance, -distance, distance);
        builder.addVertex(distance, distance, distance);
        builder.addVertex(distance, distance, -distance);

        builder.addVertex(-distance, -distance, distance);
        builder.addVertex(-distance, -distance, -distance);
        builder.addVertex(-distance, distance, -distance);
        builder.addVertex(-distance, distance, distance);

        builder.addVertex(-distance, distance, distance);
        builder.addVertex(-distance, distance, -distance);
        builder.addVertex(distance, distance, -distance);
        builder.addVertex(distance, distance, distance);

        return builder.build();
    }

    private static MeshData drawStars(Tesselator tesselator) {
        BufferBuilder builder = tesselator.begin(VertexFormat.Mode.QUADS, DefaultVertexFormat.POSITION_COLOR);

        int distance = 500;

        RandomSource random = RandomSource.create(0xAAAAA);
        // copied from vanilla, no clue what all these variables are
        for(int i = 0; i < 4500; ++i) {
            Vector3d vec = new Vector3d(random.nextFloat() * 2 - 1, random.nextFloat() * 2 - 1, random.nextFloat() * 2 - 1);
            double size = 0.15F + random.nextFloat() * 0.1F;
            size *= (distance / 100F);
            double lenSquared = vec.lengthSquared();
            if (lenSquared < 1.0D && lenSquared > 0.01D) {
                lenSquared = 1.0D / Math.sqrt(lenSquared);
                vec.mul(lenSquared);
                Vector3d scaledVec = new Vector3d(vec).mul(distance);
                double d8 = Math.atan2(vec.x, vec.z);
                double d9 = Math.sin(d8);
                double d10 = Math.cos(d8);
                double d11 = Math.atan2(Math.sqrt(vec.x * vec.x + vec.z * vec.z), vec.y);
                double d12 = Math.sin(d11);
                double d13 = Math.cos(d11);
                double d14 = random.nextDouble() * Mth.TWO_PI;
                double d15 = Math.sin(d14);
                double d16 = Math.cos(d14);

                int color = ColorUtil.getTemperatureColor(random.nextInt(1000, 40000));

                for(int j = 0; j < 4; ++j) {
                    double d18 = (double)((j & 2) - 1) * size;
                    double d19 = (double)((j + 1 & 2) - 1) * size;
                    double d21 = d18 * d16 - d19 * d15;
                    double d22 = d19 * d16 + d18 * d15;
                    double d23 = d21 * d12;
                    double d24 = -d21 * d13;
                    double d25 = d24 * d9 - d22 * d10;
                    double d26 = d22 * d9 + d24 * d10;
                    builder.addVertex((float) (scaledVec.x + d25), (float) (scaledVec.y + d23), (float) (scaledVec.z + d26)).setColor(color);
                }
            }
        }

        return builder.build();
    }
}

