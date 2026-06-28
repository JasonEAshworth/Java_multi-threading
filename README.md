# Java Multi-threading

University coursework on multithreading and parallelism in Java. Each lab parallelizes a
compute-heavy workload across worker threads and synchronizes them with `join()`.

## Labs

- **Lab02 (parallel ray tracer):** `RayTracer` splits a 512x512 image into horizontal bands,
  each rendered by a `RayThread` (Runnable) that computes sphere intersections.
  Run: `javac *.java && java RayTracer <input.txt> <numThreads>`
- **Lab03 (parallel merge sort):** compares `RecursiveMergeSorter` (sequential),
  `SequentialMergeSorter` (bottom-up iterative), and `ParallelSequentialSorter`
  (multi-threaded, using `MergeThread` workers), benchmarking 2/4/8/16 threads.
  Run: `javac *.java && java sms`
- **Lab04, Lab05:** supporting notes/resources only (no Java implementation).

The vector math classes (`Vec`, `Vec3`, `Vec4`) and the `Sphere` class were provided as
starter code; the threading implementations are my own.

## License

[MIT](LICENSE).
