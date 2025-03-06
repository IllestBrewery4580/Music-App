import pygame
import sys

# Initialize Pygame
pygame.init()

# Set up screen and window
screen = pygame.display.set_mode((600, 600))
pygame.display.set_caption('Dark Vinyl Player')

# Load images
background = pygame.image.load('dark-vines.png')
turntable = pygame.image.load('turntable.png')
turntable_rect = turntable.get_rect(center=(300, 300))

# Load music
pygame.mixer.music.load('your-song.mp3')

# Initialize variables
is_playing = False
rotation_angle = 0
spin_speed = 1

# Set up font for the cursive text
font = pygame.font.SysFont('brush script mt', 30)
text_surface = font.render("Your soul remains in this song", True, (255, 0, 0))  # Red text
text_rect = text_surface.get_rect(center=(300, 300))

# Main loop
while True:
    for event in pygame.event.get():
        if event.type == pygame.QUIT:
            pygame.quit()
            sys.exit()
        elif event.type == pygame.MOUSEBUTTONDOWN:
            if is_playing:
                pygame.mixer.music.pause()
                spin_speed = 0  # Stop turning when paused
            else:
                pygame.mixer.music.play()
                spin_speed = 1  # Normal speed when playing
            is_playing = not is_playing

        # Detect if song is skipping or rewinding
        if pygame.mixer.music.get_pos() > 0 and not is_playing:
            spin_speed = 2  # Fast spin when skipping
        if pygame.mixer.music.get_pos() < 0 and is_playing:
            spin_speed = -2  # Reverse spin when rewinding

    # Rotate the turntable
    rotation_angle += spin_speed
    if rotation_angle >= 360:
        rotation_angle = 0
    
    # Redraw everything
    screen.fill((0, 0, 0))
    screen.blit(background, (0, 0))
    rotated_turntable = pygame.transform.rotate(turntable, rotation_angle)
    rotated_rect = rotated_turntable.get_rect(center=(300, 300))
    screen.blit(rotated_turntable, rotated_rect)

    # Rotate the text with the same speed as the turntable
    rotated_text = pygame.transform.rotate(text_surface, rotation_angle)
    rotated_text_rect = rotated_text.get_rect(center=(300, 300))
    screen.blit(rotated_text, rotated_text_rect)

    pygame.display.flip()
    pygame.time.Clock().tick(60)  # ~60 FPS
