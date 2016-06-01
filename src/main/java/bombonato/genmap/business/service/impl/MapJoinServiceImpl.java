package bombonato.genmap.business.service.impl;

import bombonato.genmap.business.service.MapJoinService;

import java.util.ArrayList;
import java.util.List;

public class MapJoinServiceImpl implements MapJoinService {

    @Override
    public void mapJoin(List<Integer[]> exons) {

        List<Integer[]> introns = new ArrayList<Integer[]>();

        int index = 0;
        System.out.println("\n");
        System.out.println("--- Exons ---");
        int lastExonEnd = 0;
        for (Integer[] exon : exons) {
            System.out.println(++index + ":\t" + exon[0] + ".." + exon[1] + " = " + (exon[1] - exon[0]));

            if (lastExonEnd != 0) {
                Integer[] intron = new Integer[2];

                intron[0] = lastExonEnd + 1;
                intron[1] = exon[0] - 1;

                introns.add(intron);
            }

            lastExonEnd = exon[1];
        }

        if (introns.size() > 0) {
            index = 0;
            System.out.println("\n--- Introns ---");
            for (Integer[] intron : introns) {
                System.out.println(++index + ":\t" + intron[0] + ".." + intron[1] + " = " + (intron[1] - intron[0]));
            }
        }
    }
}
