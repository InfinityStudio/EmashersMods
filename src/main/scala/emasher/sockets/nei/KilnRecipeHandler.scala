package emasher.sockets.nei

import codechicken.nei.{NEIServerUtils, PositionedStack}
import emasher.sockets.SocketsMod
import emasher.sockets.items.ItemDusts
import net.minecraft.item.ItemStack
import net.minecraft.util.StatCollector
import scala.collection.JavaConversions._

class KilnRecipeHandler extends BaseRecipeHandler {
  final val recipeId = "sockets.kiln"

  override def getRecipeName: String = StatCollector.translateToLocal( "item.socket_module.91.name" ) + " Module"

  case object CachedKilnRecipe extends CachedRecipe {
    override def getResult: PositionedStack = {
      new PositionedStack( new ItemStack( SocketsMod.dusts, 1, ItemDusts.Const.lime.ordinal() ), 98, 18, false )
    }

    override def getIngredients: java.util.List[PositionedStack] = {

      val all = List( new PositionedStack( new ItemStack( SocketsMod.groundLimestone ), 44, 18, true ) )
      getCycledIngredients( cycleticks / 20, all )
    }
  }

  override def getGuiTexture: String = "sockets:textures/gui/nei-kiln.png"

  override def loadCraftingRecipes( result: ItemStack ): Unit = {
    if( NEIServerUtils.areStacksSameTypeCrafting( new ItemStack( SocketsMod.dusts, 1, ItemDusts.Const.lime.ordinal() ), result ) ) {
      arecipes.add( CachedKilnRecipe )
    }
  }

  override def loadCraftingRecipes( id: String, result: Object* ): Unit = {
    if( id == recipeId && this.getClass == classOf[KilnRecipeHandler] ) {
      arecipes.add( CachedKilnRecipe )
    } else if( result.nonEmpty ) {
      result.head match {
        case res: ItemStack =>
          loadCraftingRecipes( res )
        case _ =>
      }
    }
  }

  override def loadUsageRecipes( ingredient: ItemStack ): Unit = {
    if( ingredient.getItem == new ItemStack( SocketsMod.groundLimestone ).getItem ){
      arecipes.add( CachedKilnRecipe )
    }
  }
}
