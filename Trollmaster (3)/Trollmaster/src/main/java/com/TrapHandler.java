package com.trollmaster;

import net.minecraft.server.network.ServerPlayerEntity;
import net.minecraft.sound.SoundCategory;
import net.minecraft.sound.SoundEvents;
import net.minecraft.entity.effect.StatusEffectInstance;
import net.minecraft.entity.effect.StatusEffects;
import net.minecraft.text.Text;
import net.minecraft.block.Blocks;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.LightningEntity;
import net.minecraft.entity.TntEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.item.Items;

public class TrapHandler {
    
    public static void triggerTrap(int id, ServerPlayerEntity player) {
        switch (id) {
            // === NHÓM 1: ÂM THANH HÙ DỌA & TÂM LÝ (1 - 15) ===
            case 1 -> playSound(player, SoundEvents.ENTITY_CREEPER_PRIMED);
            case 2 -> playSound(player, SoundEvents.BLOCK_ANVIL_BREAK);
            case 3 -> playSound(player, SoundEvents.ENTITY_WARDEN_ANGRY);
            case 4 -> playSound(player, SoundEvents.ENTITY_ENDER_DRAGON_GROWL);
            case 5 -> playSound(player, SoundEvents.ENTITY_GHAST_SCREAM);
            case 6 -> playSound(player, SoundEvents.ENTITY_WITHER_SPAWN);
            case 7 -> playSound(player, SoundEvents.ENTITY_PHANTOM_SWOOP);
            case 8 -> playSound(player, SoundEvents.ENTITY_LIGHTNING_BOLT_THUNDER);
            case 9 -> playSound(player, SoundEvents.ENTITY_HEROBRINE_AMBIENT); // Âm thanh ma mị
            case 10 -> playSound(player, SoundEvents.ENTITY_ZOMBIE_BREAK_WOODEN_DOOR);
            case 11 -> playSound(player, SoundEvents.BLOCK_GLASS_BREAK);
            case 12 -> playSound(player, SoundEvents.ENTITY_SPIDER_STEP);
            case 13 -> playSound(player, SoundEvents.ENTITY_VEX_CHARGE);
            case 14 -> playSound(player, SoundEvents.ENTITY_GENERIC_EXPLODE);
            case 15 -> playSound(player, SoundEvents.BLOCK_CHEST_OPEN);

            // === NHÓM 2: HIỆU ỨNG TRẠNG THÁI & THỊ GIÁC (16 - 35) ===
            case 16 -> applyEffect(player, StatusEffects.BLINDNESS, 100, 1);
            case 17 -> applyEffect(player, StatusEffects.DARKNESS, 120, 1);
            case 18 -> applyEffect(player, StatusEffects.NAUSEA, 200, 2);
            case 19 -> applyEffect(player, StatusEffects.SLOWNESS, 160, 10);
            case 20 -> applyEffect(player, StatusEffects.MINING_FATIGUE, 300, 5);
            case 21 -> applyEffect(player, StatusEffects.WEAKNESS, 200, 3);
            case 22 -> applyEffect(player, StatusEffects.HUNGER, 200, 10);
            case 23 -> applyEffect(player, StatusEffects.LEVITATION, 60, 2); // Bay lên trời
            case 24 -> applyEffect(player, StatusEffects.GLOWING, 300, 1);
            case 25 -> applyEffect(player, StatusEffects.POISON, 100, 1);
            case 26 -> applyEffect(player, StatusEffects.WITHER, 80, 1);
            case 27 -> applyEffect(player, StatusEffects.UNLUCK, 600, 5);
            case 28 -> applyEffect(player, StatusEffects.INVISIBILITY, 200, 1);
            case 29 -> {
                applyEffect(player, StatusEffects.BLINDNESS, 100, 1);
                applyEffect(player, StatusEffects.NAUSEA, 100, 1);
            }
            case 30 -> applyEffect(player, StatusEffects.SPEED, 200, 10); // Quá nhanh không kiểm soát
            case 31 -> applyEffect(player, StatusEffects.JUMP_BOOST, 200, 10);
            case 32 -> applyEffect(player, StatusEffects.SLOWNESS, 300, 255); // Đóng băng di chuyển
            case 33 -> applyEffect(player, StatusEffects.BAD_OMEN, 600, 1);
            case 34 -> applyEffect(player, StatusEffects.NIGHT_VISION, 100, 1);
            case 35 -> applyEffect(player, StatusEffects.LEVITATION, 120, 5);

            // === NHÓM 3: TƯƠNG TÁC ĐỘNG LỰC HỌC & DỊCH CHUYỂN (36 - 55) ===
            case 36 -> player.addVelocity(0, 3.5, 0); // Hất tung siêu cao
            case 37 -> player.addVelocity(2.0, 0.5, 0); // Đẩy ngang mạnh
            case 38 -> player.addVelocity(-2.0, 0.5, 0);
            case 39 -> player.addVelocity(0, -3.0, 0); // Đập xuống đất
            case 40 -> player.teleport(player.getX(), player.getY() + 10, player.getZ());
            case 41 -> player.teleport(player.getX() + 5, player.getY(), player.getZ() + 5);
            case 42 -> player.teleport(player.getX() - 5, player.getY(), player.getZ() - 5);
            case 43 -> player.setYaw(player.getYaw() + 180); // Xoay ngược góc nhìn 180 độ
            case 44 -> player.setPitch(90); // Ép nhìn thẳng xuống đất
            case 45 -> player.setPitch(-90); // Ép nhìn thẳng lên trời
            case 46 -> player.teleport(player.getX(), player.getY() + 50, player.getZ()); // Rơi tự do
            case 47 -> player.addVelocity(0, 1.0, 2.0);
            case 48 -> player.addVelocity(0, 1.0, -2.0);
            case 49 -> player.teleport(player.getWorld().getSpawnPos().getX(), player.getWorld().getSpawnPos().getY(), player.getWorld().getSpawnPos().getZ()); // Về Spawn
            case 50 -> player.addVelocity(3.0, 3.0, 3.0);
            case 51 -> player.teleport(player.getX(), player.getY() - 2, player.getZ()); // Sụt hố
            case 52 -> player.setYaw(player.getYaw() + 90);
            case 53 -> player.setYaw(player.getYaw() - 90);
            case 54 -> player.addVelocity(-3.0, 1.0, -3.0);
            case 55 -> player.teleport(player.getX(), player.getY() + 100, player.getZ());

            // === NHÓM 4: THAY ĐỔI MÔI TRƯỜNG & KHỐI XUNG QUANH (56 - 80) ===
            case 56 -> player.setFireTicks(100); // Bốc cháy
            case 57 -> player.getWorld().setBlockState(player.getBlockPos(), Blocks.COBWEB.getDefaultState()); // Nhện giăng
            case 58 -> player.getWorld().setBlockState(player.getBlockPos(), Blocks.WATER.getDefaultState());
            case 59 -> player.getWorld().setBlockState(player.getBlockPos(), Blocks.LAVA.getDefaultState());
            case 60 -> player.getWorld().setBlockState(player.getBlockPos().down(), Blocks.ICE.getDefaultState()); // Trơn trượt
            case 61 -> player.getWorld().setBlockState(player.getBlockPos().up(2), Blocks.ANVIL.getDefaultState()); // Đe rơi vào đầu
            case 62 -> player.getWorld().setBlockState(player.getBlockPos(), Blocks.POWDER_SNOW.getDefaultState()); // Tuyết đóng băng
            case 63 -> {
                // Sét đánh bên cạnh
                LightningEntity bolt = EntityType.LIGHTNING_BOLT.create(player.getWorld());
                if (bolt != null) {
                    bolt.refreshPositionAfterTeleport(player.getX() + 1, player.getY(), player.getZ() + 1);
                    player.getWorld().spawnEntity(bolt);
                }
            }
            case 64 -> {
                // Thả TNT ngòi nổ sát người
                TntEntity tnt = EntityType.TNT.create(player.getWorld());
                if (tnt != null) {
                    tnt.refreshPositionAfterTeleport(player.getX(), player.getY(), player.getZ());
                    tnt.setFuse(40);
                    player.getWorld().spawnEntity(tnt);
                }
            }
            case 65 -> player.getWorld().setBlockState(player.getBlockPos().down(), Blocks.TNT.getDefault