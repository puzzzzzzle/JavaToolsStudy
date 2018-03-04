package group.zhangtao.design.pattern.decorator;

public class WDecoratorSkills extends DecoratorSkills {
    public WDecoratorSkills(Player player) {
        super(player);
    }

    @Override
    public void useSkills() {
        super.useSkills();
        useThisSkill();
    }

    private void useThisSkill(){
        System.out.println("使用了W技能!");
    }
}
