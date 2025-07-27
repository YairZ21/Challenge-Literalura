package com.tuempresa.desafiobusquedalibros.external.dto;

import java.util.List;

public record BookDto(Long id, String title, List<AuthorDto> authors, List<String> languages, Integer download_count) {}
