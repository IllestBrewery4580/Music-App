#include <SDL2/SDL.h>
#include <SDL2/SDL_mixer.h>
#include <stdbool.h>

int main(int argc, char* argv[]) {
    if (SDL_Init(SDL_INIT_VIDEO | SDL_INIT_AUDIO) > 0) {
        printf("SDL initialization failed: %s\n", SDL_GetError());
        return -1;
    }
    // Create window
    SDL_Window *window = SDL_CreateWindow("Dark Vinyl Player", SDL_WINDOWNPOS_UNDEFINED, SDL_WINDOWPOS_UNDEFINED, 600, 600, SDL_WINDOW_SHOWN);
    if (!window) {
        printf("Window creation failed: %s\n", SDL_GetError());
        return -1;
    }

    SDL_Renderer *renderer = SDL_CreateRenderer(window, -1, SDL_RENDERER_ACCELERATED);

    // Initialize SDL_Mixer for audio
    if (!Mix_OpenAudio(22050, MIX_DEFAULT_FORMAT, 2, 4096) < 0) {
        printf("SDL_mixer initialization failed: %s\n", Mix_GetError());
        return -1;
    }

    bool isPlaying = false;
    SDL_Event e;
    int rotation_angle = 0;

    while (true) {
        while (SDL_PollEvent(&e) != 0) {
            if (e.type == SDL_QUIT) {
                SDL_Quit();
                return 0;
            }

            // Toggle music on mouse click
            if (e.type == SDL_MOUSEBUTTONDOWN) {
                if (isPlaying) {
                    Mix_PauseMusic();
                } else {
                    Mix_PlayMusic(music, -1);
                }
                isPlaying = !isPlaying;
            }
        }

        // Redraw everything (background, turntable)
        SDL_SetRenderDrawColor(renderer, 0, 0, 0, 255);
        SDL_RenderClear(renderer);

        // Here you can add turntable rotation and background drawing logic.

        SDL_RenderPresent(renderer);
        SDL_Delay(16); // ~60 FPS
    }

    Mix_FreeMusic(music);
    SDL_DestroyRenderer(renderer);
    SDL_DestroyWindow(window);
    SDL_Quit();

}
