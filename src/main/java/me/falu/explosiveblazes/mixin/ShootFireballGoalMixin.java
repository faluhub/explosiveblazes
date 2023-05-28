package me.falu.explosiveblazes.mixin;

import net.minecraft.entity.Entity;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.mob.BlazeEntity;
import net.minecraft.entity.projectile.FireballEntity;
import net.minecraft.util.math.Vec3d;
import net.minecraft.world.World;
import org.spongepowered.asm.mixin.Final;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Redirect;

@Mixin(BlazeEntity.ShootFireballGoal.class)
public class ShootFireballGoalMixin {
    @Shadow @Final private BlazeEntity blaze;

    @Redirect(method = "tick", at = @At(value = "INVOKE", target = "Lnet/minecraft/world/World;spawnEntity(Lnet/minecraft/entity/Entity;)Z"))
    private boolean spawnFireball(World instance, Entity entity) {
        LivingEntity livingEntity = this.blaze.getTarget();
        if (livingEntity != null) {
            Vec3d vec3d = this.blaze.getRotationVec(1.0f);
            double f = livingEntity.getX() - (this.blaze.getX() + vec3d.x * 4.0);
            double g = livingEntity.getBodyY(0.5) - (0.5 + this.blaze.getBodyY(0.5));
            double h = livingEntity.getZ() - (this.blaze.getZ() + vec3d.z * 4.0);
            FireballEntity fireballEntity = new FireballEntity(instance, this.blaze, f, g, h);
            fireballEntity.updatePosition(this.blaze.getX() + vec3d.x * 4.0, this.blaze.getBodyY(0.5) + 0.5, fireballEntity.getZ() + vec3d.z * 4.0);
            return instance.spawnEntity(fireballEntity);
        }
        return instance.spawnEntity(entity);
    }
}
