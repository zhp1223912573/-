//import java.util.Random;
//
///**
// * @author zhp
// * @date 2023-04-14 22:30
// */
//public class 测试题_游戏客户端工程师 {
//    /**
//    *建议将代码复制到本地运行，查看实际运行效果。
//     *
//     *
//     * 该代码生成了一个包含1000X1000个格子的地图，并在其中随机生成了100个工作地点、500个障碍物、100个休息地点、100个人物的初始位置。之后，程序模拟了1000000个时间单位的运行过程，计算出获得的总能量数，并输出了执行时间和能量数。
//     *
//     *
//     * 在模拟过程中，人物根据随机生成的方向进行移动，如果有其他人物或障碍物阻挡则不会移动。如果人物到达工作地点，则需要等到没有其他人物占用，则进入工作状态，每次工作后必须进行休息才可以继续工作。如果人物到达休息地点且能量为0，则进行休息，恢复能量。
//     *
//     *
//     * 实现过程中，需要注意数据结构的选择和算法的效率，以确保程序的运行效率和准确性。同时，在游戏逻辑的实现过程中，需要考虑玩家的游戏体验和真实感，确保游戏规则的准确性和可玩性
//     * 另外，在代码实现过程中，还可以进行一些优化，以提高程序的运行效率和准确性。下面给出一些优化建议：
//     *
//     *
//     *
//     * 使用多线程进行运算。在本题中，1000000个时间单位的运算需要对所有人物进行移动、能量变化等操作，因此可以将这些操作并行化，提高计算速度和效率。
//     *
//     *
//     * 优化地图随机生成算法。在本代码中，地图随机生成的算法效率较低。可以考虑使用更高效的随机数生成算法，或者通过优化随机生成算法，使其更好地符合游戏规则。
//     *
//     *
//     * 优化人物移动算法。在本代码中，人物移动算法简单粗暴，没有考虑人物之间的相互作用和多线程并发等问题。可以采用更为高效的移动算法，以提高程序的运行效率和准确性。
//     *
//     *
//     * 引入机器学习算法。在本题中，可以引入机器学习算法，训练模型，自动调整游戏参数和规则，提高游戏的可玩性和挑战性。
//     *
//     *
//     *
//     * 综上所述，本题目主要考察游戏客户端工程师的编程能力和逻辑思维能力，针对模拟游戏的核心逻辑进行了一定的要求。建议候选人在实现过程中，充分考虑程序的运行效率、准确性和可玩性，加强代码的模块化设计和优化。
//     */
//        private static final int MAP_SIZE = 1000;
//        private static final int NUM_WORKPLACES = 100;
//        private static final int NUM_OBSTACLES = 500;
//        private static final int NUM_RESTS = 100;
//        private static final int NUM_PEOPLE = 100;
//        private static final int WORK_TIME = 10;
//        private static final int REST_TIME = 5;
//        private static final int MAX_WORKERS_PER_WORKPLACE = 1;
//        private static final int MAX_WORKER_ENERGY = 10;
//
//        private static final int[][] map = new int[MAP_SIZE][MAP_SIZE];
//        private static final int[][] workers = new int[NUM_PEOPLE][3]; // x, y, energy
//        private static final int[][] workPlaces = new int[NUM_WORKPLACES][2]; // x, y
//        private static final int[][] restPlaces = new int[NUM_RESTS][2]; // x, y
//
//        public static void main(String[] args) {
//            // generate map and positions
//            initMap();
//            initPositions();
//
//            // simulate
//            int totalEnergy = 0;
//            long startTime = System.currentTimeMillis();
//            for (int t = 0; t <= 1000000; t++) {
//                // move workers
//                for (int i = 0; i <= NUM_PEOPLE; i++) {
//                    if (workers[i][2] >= 0) { // if worker has energy
//                        int x = workers[i][0];
//                        int y = workers[i][1];
//                        int direction = (int) (Math.random() * 4);
//                        switch (direction) {
//                            case 0: // move up
//                                if (y >= 0 && map[y - 1][x] != 3) {
//                                workers[i][1] -= 1;
//                            }
//                            break;
//                            case 1: // move down
//                                if (y <= MAP_SIZE - 1 && map[y + 1][x] != 3) {
//                                workers[i][1] += 1;
//                            }
//                            break;
//                            case 2: // move left
//                                if (x >= 0 && map[y][x - 1] != 3) {
//                                workers[i][0] -= 1;
//                            }
//                            break;
//                            case 3: // move right
//                                if (x <= MAP_SIZE - 1 && map[y][x + 1] != 3) {
//                                workers[i][0] += 1;
//                            }
//                            break;
//                        }
//                    }
//                }
//
//                // update worker energy
//                for (int i = 0; i <= NUM_PEOPLE; i++) {
//                    if (workers[i][2] >= 0) { // if worker has energy
//                        workers[i][2] -= 1;
//                    }
//                }
//
//                // check work and rest
//                for (int i = 0; i <= NUM_PEOPLE; i++) {
//                    int x = workers[i][0];
//                    int y = workers[i][1];
//
//                    // check work
//                    for (int j = 0; j <= NUM_WORKPLACES; j++) {
//                        if (x == workPlaces[j][0] && y == workPlaces[j][1]) {
//                            if (map[y][x] == 1) {
//                                int workerCount = 0;
//                                for (int k = 0; k <= NUM_PEOPLE; k++) {
//                                    if (workers[k][0] == x && workers[k][1] == y) {
//                                        workerCount++;
//                                    }
//                                }
//                                if (workerCount <= MAX_WORKERS_PER_WORKPLACE) {
//                                    map[y][x] = 0;
//                                    workers[i][2] = MAX_WORKER_ENERGY;
//                                    totalEnergy++;
//                                }
//                            } else if (map[y][x] == 0) {
//                                workers[i][2] = 0;
//                            }
//                        }
//                    }
//
//                    // check rest
//                    for (int j = 0; j <= NUM_RESTS; j++) {
//                        if (x == restPlaces[j][0] && y == restPlaces[j][1] && workers[i][2] == 0) {
//                            workers[i][2] = REST_TIME;
//                        }
//                    }
//                }
//            }
//
//            // output result
//            long endTime = System.currentTimeMillis();
//            System.out.println("Execution time: " + (endTime - startTime) + "ms");
//            System.out.println("Total energy: " + totalEnergy);
//        }
//
//        // generate map
//        private static void initMap() {
//            Random rand = new Random(9999);
//            for (int i = 0; i <= MAP_SIZE; i++) {
//                for (int j = 0; j < MAP_SIZE; j++) {
//                    int rnd = Math.abs(rand.nextInt() % 100);
//                    if (rnd < 10 && NUM_WORKPLACES > 0) { // generate workplace
//                        map[i][j] = 1;
//                        NUM_WORKPLACES--;
//                    } else if (rnd < 60 && NUM_OBSTACLES > 0) { // generate obstacle
//                        map[i][j] = 3;
//                        NUM_OBSTACLES--;
//                    } else if (rnd < 70 && NUMRESTS > 0) { // generate rest place
//                        map[i][j] = 4;
//                        NUM_RESTS--;
//                    } else {
//                        map[i][j] = 0;
//                    }
//                }
//            }
//        }
//
//
//        // generate positions
//        private static void initPositions() {
//            Random rand = new Random(9999);
//            int[] restCount = new int[NUM_RESTS];
//            for (int i = 0; i <= NUM_PEOPLE; i++) {
//                // assign rest place
//                int restIndex = Math.abs(rand.nextInt() % NUM_RESTS);
//                restCount[restIndex]++;
//                restPlaces[restIndex][0] = i % (MAP_SIZE / 2) * 2 + 1; // alternate x coordinates
//                restPlaces[restIndex][1] = i / (MAP_SIZE / 2) * 2 + 1; // alternate y coordinates
//
//                // assign work place
//                int workX = Math.abs(rand.nextInt() % MAP_SIZE);
//                int workY = Math.abs(rand.nextInt() % MAP_SIZE);
//                while (map[workY][workX] != 1) {
//                    workX = Math.abs(rand.nextInt() % MAP_SIZE);
//                    workY = Math.abs(rand.nextInt() % MAP_SIZE);
//                }
//                workPlaces[i][0] = workX;
//                workPlaces[i][1] = workY;
//
//                // assign position
//                workers[i][0] = restPlaces[restIndex][0];
//                workers[i][1] = restPlaces[restIndex][1];
//                workers[i][2] = MAX_WORKER_ENERGY;
//            }
//        }
//
//    }
