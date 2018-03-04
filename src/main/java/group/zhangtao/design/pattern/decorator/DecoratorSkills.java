package group.zhangtao.design.pattern.decorator;

public abstract class DecoratorSkills implements Player {
    private Player player;
    public DecoratorSkills(Player player){
        this.player=player;
    }

    @Override
    public void useSkills() {
        player.useSkills();
    }
}
