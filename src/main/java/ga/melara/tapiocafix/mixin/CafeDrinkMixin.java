package ga.melara.tapiocafix.mixin;

import com.Imphuls3.createcafe.common.item.foods.CafeDrink;
import com.Imphuls3.createcafe.core.registry.ItemRegistry;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.ItemLike;
import net.minecraft.world.level.Level;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

@Mixin(CafeDrink.class)
public class CafeDrinkMixin extends Item {

    //dummy
    public CafeDrinkMixin(Properties properties) {
        super(properties);
    }

    @Inject(method = "finishUsingItem", at=@At("HEAD"), cancellable = true)
    public void onFinishUsingItem(ItemStack stack, Level level, LivingEntity livingEntity, CallbackInfoReturnable<ItemStack> cir){
        if(livingEntity instanceof Player player) {
            ItemStack itemstack = super.finishUsingItem(stack, level, livingEntity);
            player.getInventory().add(new ItemStack((ItemLike) ItemRegistry.EMPTY_BOBA_CUP.get()));
            cir.setReturnValue(itemstack);
        }
    }
}
