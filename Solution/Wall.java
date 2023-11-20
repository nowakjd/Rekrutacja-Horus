import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class Wall implements Structure {
    private List<Block> blocks;

    @Override
    public Optional<Block> findBlockByColor(String color) {
        return unpackList(blocks)
                .stream()
                .filter(b -> b.getColor().equals(color))
                .findAny();

    }

    @Override
    public List<Block> findBlocksByMaterial(String material) {
        return unpackList(blocks)
                .stream()
                .filter(block -> block.getMaterial().equals(material))
                .toList();
    }

    @Override
    public int count() {
        return unpackList(blocks).size();
    }

    private static List<Block> unpackBlock(Block block) {
        if (block instanceof CompositeBlock) {
            return unpackList(((CompositeBlock) block).getBlocks());
        } else return List.of(block);
    }

    private static List<Block> unpackList(List<Block> list) {
        List<Block> result = new ArrayList<>();
        for (Block block : list) {
            result.addAll(Wall.unpackBlock(block));
        }
        return result;
    }
}